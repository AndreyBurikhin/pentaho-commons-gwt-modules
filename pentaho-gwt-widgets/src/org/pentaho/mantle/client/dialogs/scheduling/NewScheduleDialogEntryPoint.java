package org.pentaho.mantle.client.dialogs.scheduling;

import org.pentaho.gwt.widgets.client.utils.i18n.IResourceBundleLoadCallback;
import org.pentaho.gwt.widgets.client.utils.i18n.ResourceBundle;

import org.pentaho.mantle.client.messages.Messages;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class NewScheduleDialogEntryPoint implements EntryPoint, IResourceBundleLoadCallback {
  @Override
  public void onModuleLoad() {
    ResourceBundle messages = new ResourceBundle();
    Messages.setResourceBundle( messages );
    messages.loadBundle(
        GWT.getModuleBaseURL() + "messages/", "mantleMessages", true, NewScheduleDialogEntryPoint.this ); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Override
  public void bundleLoaded( String bundleName ) {
    setupNativeHooks( this );
  }

  public void openDialog() {
    NewScheduleDialog dialog = new NewScheduleDialog( "/home/admin", null, false );
    dialog.show();
  }

  public native void setupNativeHooks( NewScheduleDialogEntryPoint reportSchedulingEntryPoint )
  /*-{
    $wnd.openReportSchedulingDialog = function() {
      reportSchedulingEntryPoint.@org.pentaho.mantle.client.dialogs.scheduling.NewScheduleDialogEntryPoint::openDialog()();
    }
     }-*/;
}


