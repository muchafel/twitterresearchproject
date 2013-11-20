package ude.SocialMediaExplorer.client.rmi;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface AnalyticServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see ude.SocialMediaExplorer.client.rmi.AnalyticService
     */
    void analyseHashtag( java.lang.String hashtag, AsyncCallback<ude.SocialMediaExplorer.shared.Response> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static AnalyticServiceAsync instance;

        public static final AnalyticServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (AnalyticServiceAsync) GWT.create( AnalyticService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
