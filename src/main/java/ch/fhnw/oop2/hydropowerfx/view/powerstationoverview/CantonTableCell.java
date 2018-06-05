package ch.fhnw.oop2.hydropowerfx.view.powerstationoverview;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class CantonTableCell extends TableCell<PowerStationPM, Canton> {
    private static final Map<String, Image> WAPPEN = new HashMap<>();

    private static final Insets INSETS = new Insets(1, 8, 1, 5);

    @Override
    protected void updateItem(Canton item, boolean empty) {
        setText("");
        setGraphic(null);
        if (!empty) {
            String canton = item.getName();
            Image img = WAPPEN.get(item);

            if (img == null) {
                img = new Image(getClass().getResource("wappen_klein/" + Canton.getCanton(canton) + ".png")
                        .toExternalForm(), 18, 18, true, true, true);
                WAPPEN.put(canton, img);
            }

            ImageView imageView = new ImageView(img);

            setGraphic(imageView);
            setTooltip(new Tooltip(canton));
            setAlignment(Pos.CENTER);
            setPadding(INSETS);
        }

    }
}
