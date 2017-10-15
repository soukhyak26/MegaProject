package com.affaince.subscription.subscriber.web.controller;

import com.affaince.subscription.common.upload.ConfigurationUploadController;
import com.affaince.subscription.common.upload.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mandar on 10/15/2017.
 */
@RestController("SubscriberMetadataUploadController")
@RequestMapping("subscriber/upload")
public class SubscriberMetadataUploadController extends ConfigurationUploadController {

    @Autowired
    public SubscriberMetadataUploadController(StorageService storageService) {
        super(storageService);
    }

}
