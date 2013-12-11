package monitoring.listener;


import com.sun.jersey.api.model.AbstractResourceModelContext;
import com.sun.jersey.api.model.AbstractResourceModelListener;
import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ext.Provider;

@Provider
public class Listener implements AbstractResourceModelListener {

    @Override
    public void onLoaded(AbstractResourceModelContext modelContext) {
        try {
            RxtxConnection.getInstance();
        } catch (PortInUseException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}