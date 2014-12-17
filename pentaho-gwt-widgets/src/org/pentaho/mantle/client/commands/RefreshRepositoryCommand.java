/*!
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2002-2013 Pentaho Corporation..  All rights reserved.
 */

package org.pentaho.mantle.client.commands;

import org.pentaho.mantle.client.solutionbrowser.RepositoryFileTreeManager;
import org.pentaho.mantle.client.usersettings.IMantleUserSettingsConstants;
import org.pentaho.mantle.client.usersettings.JsSetting;
import org.pentaho.mantle.client.usersettings.UserSettingsManager;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RefreshRepositoryCommand extends AbstractCommand {

  private static native void setupNativeHooks( RefreshRepositoryCommand cmd )
  /*-{
    $wnd.mantle_refreshRepository = function() {
      cmd.@org.pentaho.mantle.client.commands.RefreshRepositoryCommand::execute(Z)(false);
    }
  }-*/;

  public RefreshRepositoryCommand() {
    setupNativeHooks( this );
  }

  public void execute() {
    super.execute();
    RepositoryFileTreeManager.getInstance().beforeFetchRepositoryFileTree();
  }

  protected void performOperation( final boolean feedback ) {
    UserSettingsManager.getInstance().getUserSettings( new AsyncCallback<JsArray<JsSetting>>() {

      public void onSuccess( JsArray<JsSetting> settings ) {
        boolean showHiddenFiles = false;
        if ( settings != null ) {
          for ( int i = 0; i < settings.length(); i++ ) {
            JsSetting setting = settings.get( i );
            if ( IMantleUserSettingsConstants.MANTLE_SHOW_HIDDEN_FILES.equals( setting.getName() ) ) {
              showHiddenFiles = Boolean.parseBoolean( setting.getValue() );
            }
          }
        }
        RepositoryFileTreeManager.getInstance().fetchRepositoryFileTree( true, null, null, showHiddenFiles );
      }

      public void onFailure( Throwable caught ) {
        RepositoryFileTreeManager.getInstance().fetchRepositoryFileTree( true, null, null, false );
      }
    }, false );
  }

  protected void performOperation() {
    performOperation( true );
  }

}
