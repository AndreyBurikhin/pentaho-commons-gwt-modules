package org.pentaho.mantle.client.dialogs.scheduling;

import com.google.gwt.core.client.EntryPoint;

public class NewScheduleDialogEntryPoint implements EntryPoint {
  @Override
  public void onModuleLoad() {
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
