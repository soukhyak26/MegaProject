package payments.processors.reg;

import com.affaince.payments.grammer.PaymentsLexer;
import com.affaince.payments.grammer.PaymentsParser;
import com.affaince.payments.scheme.Scheme;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SchemeRegistrationProcessor {

    public Scheme registerScheme(String schemeDefinitionString){
        PaymentsLexer lexer = new PaymentsLexer(CharStreams.fromString(schemeDefinitionString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PaymentsParser parser = new PaymentsParser(tokens);
        ParseTree tree = parser.scheme();
        ParseTreeWalker walker = new ParseTreeWalker();


        Scheme scheme = new Scheme();
        PaymentSchemeListener listener = new PaymentSchemeListener(scheme);
        walker.walk(listener, tree);
        scheme = listener.getScheme();  //plain parsed scheme without any inputs.. due for registration
        return scheme;
    }
}
