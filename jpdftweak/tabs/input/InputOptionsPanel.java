package jpdftweak.tabs.input;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Vasilis Naskos
 */
public class InputOptionsPanel extends JPanel {
    
    private final CellConstraints cc = new CellConstraints();
    private JCheckBox batchProcessing, interleave, mergeByDir;
    private JTextField interleaveSize;

    public InputOptionsPanel() {
        super(new FormLayout("f:p, 40dlu, $lcgap, f:p:g, 40dlu", "f:p, f:p"));
        
        initializeComponents();
        positionComponents();
    }
    
    private void initializeComponents() {
        interleave = new JCheckBox("Interleave documents in blocks of");

        interleaveSize = new JTextField("1", 10);
        interleaveSize.setEnabled(false);
        interleave.addItemListener(interleaveListener());
        
        mergeByDir = new JCheckBox("Merge by directory");
        mergeByDir.addItemListener(mergeByDirListener());
        
        batchProcessing = new JCheckBox("Batch Process");
        batchProcessing.addItemListener(batchProcessingListener());
    }
    
    private ItemListener interleaveListener() {
        return new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                interleaveItemStateChanged();
            }
        };
    }
    
    private void interleaveItemStateChanged() {
        interleaveSize.setEnabled(interleave.isSelected());
    }

    private ItemListener mergeByDirListener() {
        return new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                mergeByDirItemStateChanged(e);
            }
        };
    }

    private void mergeByDirItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == 1) {
            batchProcessing.setSelected(false);
            interleave.setEnabled(true);
        }
    }

    private ItemListener batchProcessingListener() {
        return new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                batchProcessingItemStateChanged(e);
            }
        };
    }

    private void batchProcessingItemStateChanged(ItemEvent e) {
        if (e.getStateChange() == 1) {
            mergeByDir.setSelected(false);
            interleave.setEnabled(false);
            interleave.setSelected(false);
        } else {
            interleave.setEnabled(true);
        }
    }
    
    private void positionComponents() {
        this.add(interleave, cc.xy(1, 2));
        this.add(interleaveSize, cc.xy(2, 2));
        this.add(mergeByDir, cc.xyw(1, 1, 2));
        this.add(batchProcessing, cc.xyw(4, 1, 2));
    }
    
    public boolean isMergeByDirSelected() {
        return mergeByDir.isSelected();
    }
    
    public boolean isBatchSelected() {
        return batchProcessing.isSelected();
    }
    
    public boolean isInterleaveSelected() {
        return interleave.isSelected();
    }
    
    public String getInterleaveSize() {
        return interleaveSize.getText();
    }
}
