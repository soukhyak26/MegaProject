package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.repository.DefaultIdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by rbsavaliya on 26-12-2016.
 */
public class SubscriptionTestDataGenerator {

    private static List<String> productIds = new ArrayList<>();
    private static int subscriptionCount;
    private ClassLoader classLoader = getClass().getClassLoader();

    public int getSubscriptionCount() {
        File file = new File(classLoader.getResource(".").getPath() + "/subscriptioncount");
        try (InputStream fileInputStream = new FileInputStream(file)) {
            byte [] values = new byte[fileInputStream.available()];
            fileInputStream.read(values);
            subscriptionCount = new Integer(new String(values));
        } catch (IOException e) {
            return 0;
        }
        return subscriptionCount;
    }

    public static void main(String[] args) throws IOException {
        new SubscriptionTestDataGenerator().readProductIdsToFile();
    }

    public SubscriptionTestDataGenerator generate() throws IOException {

        readProductIdsToFile ();
        generateSubscriptionData();
        generateSubscriberData();
        writeSubscriptionCountToFile ();
        return this;
    }

    private void readProductIdsToFile() {
        File file = new File(classLoader.getResource(".").getPath() + "/productids");
        try (Stream<String> stream = Files.lines(Paths.get(classLoader.getResource(".").getPath() + "/productids"))) {
            stream.forEach(productIds::add);
            productIds.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSubscriptionCountToFile() {
        File file = new File(classLoader.getResource(".").getPath() + "/subscriptioncount");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write((subscriptionCount+"").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateSubscriberData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Subscriber> subscribers = new ArrayList<>();
        long sdc = getSubscriptionCount();
        for (int i = 0; i < this.subscriptionCount; i++) {
            Subscriber subscriber = new Subscriber("Mr", "TestSubscriber" + i, "", "lastName" + i,
                    "A1-504", "Casa 7", "Pune", "MH", "India", "411033",
                    "testemail" + i + "@affaince.com", new Random().nextLong() * 100000000L + "", ""
            );
            subscribers.add(subscriber);
        }
        File file = new File(classLoader.getResource(".").getPath() + "/Subscribers.json");
        try(OutputStream fileOutputStream = new FileOutputStream(file);) {
            fileOutputStream.write(objectMapper.writeValueAsBytes(subscribers));
            /*Files.write(Paths.get(classLoader.getResource(".").toURI().toString() + "/Subscribers.json"),
                    objectMapper.writeValueAsBytes(subscribers),
                    StandardOpenOption.CREATE);*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateSubscriptionData() throws JsonProcessingException {
        Map<String, Integer> lineNumberTracker = new HashMap<>();
        Map<String, SubscriptionItem> subscriptionItemMap = new HashMap<>();
        productIds.forEach(productId -> {
            long totalForecastCounts = getTotalSubscriptionCountFromActiveForecast(productId);
            long totalBasketsToBeCreated = totalForecastCounts
                    + (totalForecastCounts * 10 / 100);
            ObjectMapper objectMapper = new ObjectMapper();
            int i = 0;
            long sdc = getSubscriptionCount();
            while (totalBasketsToBeCreated >= 0) {
                int noOfCycle = new Random().nextInt(9) + 3;
                BasketItemRequest basketItemRequest = new BasketItemRequest(
                        productId,
                        1,
                        new Period(1, PeriodUnit.MONTH),
                        getMrpByProductId(productId),
                        getMrpByProductId(productId),
                        noOfCycle
                );
                String subscriberId = new DefaultIdGenerator().generator("testemail" + i + "@affaince.com");
                String fileName = classLoader.getResource(".").getPath() + "/" + subscriberId + ".json";
                if (lineNumberTracker.get(fileName) != null &&
                        lineNumberTracker.get(fileName).intValue() == 20) {
                    i++;
                    continue;
                }
                totalBasketsToBeCreated -= noOfCycle;
                if (subscriptionItemMap.get(fileName) != null) {
                    subscriptionItemMap.get(fileName).getBasketItemRequests().add(basketItemRequest);
                } else {
                    SubscriptionItem subscriptionItem = new SubscriptionItem();
                    subscriptionItem.setBasketItemRequests(new ArrayList<>());
                    subscriptionItemMap.put(fileName, subscriptionItem);
                    subscriptionItemMap.get(fileName).getBasketItemRequests().add(basketItemRequest);
                }
                if (lineNumberTracker.containsKey(fileName)) {
                    lineNumberTracker.put(fileName,
                            lineNumberTracker.get(fileName).intValue() + 1);
                } else {
                    lineNumberTracker.put(fileName, 1);
                }
                i++;
            }
            this.subscriptionCount = subscriptionItemMap.size();

        });
        ObjectMapper objectMapper = new ObjectMapper();
        for (String fileName:
             subscriptionItemMap.keySet()) {
            File file = new File(fileName);
            try (OutputStream outputStream = new FileOutputStream(file);) {
                String s = objectMapper.writeValueAsString(subscriptionItemMap.get(fileName));
                outputStream.write(s.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private long getTotalSubscriptionCountFromActiveForecast (String productId) {
        Long totalForecstedSubscription = 0L;
        try {
            URL url = new URL("http://localhost:8082/forecast/lastforecast/totalsubscription/" + productId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            totalForecstedSubscription = Long.parseLong(br.readLine());

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalForecstedSubscription;
    }

    private Double getMrpByProductId (String productId) {
        Double totalForecstedSubscription = 0.0;
        try {
            URL url = new URL("http://localhost:8082/product/mrp/" + productId);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            totalForecstedSubscription = Double.parseDouble(br.readLine());

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalForecstedSubscription;
    }
}