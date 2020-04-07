import com.sun.jdi.Bootstrap;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;

import java.util.List;
import java.util.Map;


/**
 * @author fanrey
 */
public class JDITest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String className = null;
        if (args != null && args.length == 1) {
            className = args[0];
        } else {
            className = "no.axxessit.il.axxrpc.AxxRpcPinger";
        }
        Connector conn = null;
        List<Connector> connectors = Bootstrap.virtualMachineManager().allConnectors();
        for (Connector connector : connectors) {
            if (connector.name().equals("com.sun.jdi.SocketAttach")) {
                conn = connector;
            }
        }

        Map<String, Connector.Argument> arguments = conn.defaultArguments();
        for (Map.Entry<String, Connector.Argument> entry : arguments.entrySet()) {
            Connector.Argument argument = entry.getValue();
        }
        Connector.Argument host = arguments.get("hostname");
        Connector.Argument port = arguments.get("port");
        host.setValue("localhost");
        port.setValue("8888");

        VirtualMachine vm = null;
        AttachingConnector attacher = (AttachingConnector) conn;
        try {
            vm = attacher.attach(arguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ReferenceType> classList = vm.classesByName(className);
//        System.out.println(classList.size());
//        List<ReferenceType> classList = vm.allClasses();
        for (ReferenceType type : classList) {
            List<ObjectReference> ors = type.instances(0);
            System.out.println(type + " instance number: " + ors.size());
        }
        List<ThreadReference> ts = vm.allThreads();
        System.out.println("Thread number: " + ts.size());
    }

}
