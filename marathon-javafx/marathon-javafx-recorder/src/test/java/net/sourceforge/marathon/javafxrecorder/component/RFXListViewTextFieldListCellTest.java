/*******************************************************************************
 * Copyright 2016 Jalian Systems Pvt. Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package net.sourceforge.marathon.javafxrecorder.component;

import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.Pane;
import net.sourceforge.marathon.javafx.tests.TextFieldListViewSample;
import net.sourceforge.marathon.javafxrecorder.component.LoggingRecorder.Recording;

public class RFXListViewTextFieldListCellTest extends RFXComponentTest {

    @Test
    public void select() {
        @SuppressWarnings("unchecked")
        ListView<String> listView = (ListView<String>) getPrimaryStage().getScene().getRoot().lookup(".list-view");
        LoggingRecorder lr = new LoggingRecorder();
        Platform.runLater(() -> {
            @SuppressWarnings("unchecked")
            TextFieldListCell<String> cell = (TextFieldListCell<String>) getCellAt(listView, 3);
            Point2D point = getPoint(listView, 3);
            RFXListView rfxListView = new RFXListView(listView, null, point, lr);
            rfxListView.focusGained(rfxListView);
            cell.startEdit();
            cell.updateItem("Item 4 Modified", false);
            cell.commitEdit("Item 4 Modified");
            rfxListView.focusLost(rfxListView);
        });
        List<Recording> recordings = lr.waitAndGetRecordings(1);
        Recording recording = recordings.get(0);
        AssertJUnit.assertEquals("recordSelect", recording.getCall());
        AssertJUnit.assertEquals("Item 4 Modified", recording.getParameters()[0]);
    }

    @Override
    protected Pane getMainPane() {
        return new TextFieldListViewSample();
    }
}
