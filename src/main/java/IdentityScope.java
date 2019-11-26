import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class IdentityScope {


    public boolean isAdamin(){
        return true;
    }
}
