package battleshipGUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

public class UI {

	protected Shell shell;
	private Table grid;
	private Table ships;
	private Button btnRandomize;
	private Text txtBattleship;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UI window = new UI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Button btnQuit = new Button(shell, SWT.NONE);
		FormData fd_btnQuit = new FormData();
		fd_btnQuit.bottom = new FormAttachment(100, -33);
		fd_btnQuit.left = new FormAttachment(0, 10);
		btnQuit.setLayoutData(fd_btnQuit);
		btnQuit.setText("Quit");
		
		Button btnFire = new Button(shell, SWT.NONE);
		FormData fd_btnFire = new FormData();
		fd_btnFire.top = new FormAttachment(btnQuit, 0, SWT.TOP);
		fd_btnFire.left = new FormAttachment(btnQuit, 163);
		btnFire.setLayoutData(fd_btnFire);
		btnFire.setText("Fire");
		
		grid = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		grid.setSelection(new int[] {16});
		grid.setSelection(16);
		FormData fd_grid = new FormData();
		fd_grid.bottom = new FormAttachment(btnFire, -67);
		grid.setLayoutData(fd_grid);
		grid.setHeaderVisible(true);
		grid.setLinesVisible(true);
		
		ships = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		fd_grid.right = new FormAttachment(ships, -98);
		FormData fd_ships = new FormData();
		fd_ships.top = new FormAttachment(0, 38);
		fd_ships.bottom = new FormAttachment(100, -60);
		fd_ships.right = new FormAttachment(100);
		ships.setLayoutData(fd_ships);
		ships.setHeaderVisible(true);
		ships.setLinesVisible(true);
		
		btnRandomize = new Button(shell, SWT.NONE);
		btnRandomize.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
			}
		});
		FormData fd_btnRandomize = new FormData();
		fd_btnRandomize.top = new FormAttachment(btnQuit, 0, SWT.TOP);
		fd_btnRandomize.right = new FormAttachment(100, -10);
		btnRandomize.setLayoutData(fd_btnRandomize);
		btnRandomize.setText("Randomize");
		
		txtBattleship = new Text(shell, SWT.BORDER);
		txtBattleship.setText("Battleship");
		FormData fd_txtBattleship = new FormData();
		fd_txtBattleship.top = new FormAttachment(0, 10);
		fd_txtBattleship.right = new FormAttachment(btnFire, 0, SWT.RIGHT);
		txtBattleship.setLayoutData(fd_txtBattleship);

	}
}
