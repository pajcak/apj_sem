package org.sem.view;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.sem.business.FacadeService;
import org.sem.model.Game;
import static org.sem.utils.Messages.*;
import org.sem.utils.MyException;
import org.sem.view.impl.Refreshable;

/**
 *
 * @author Skarab
 */
public final class GameModel extends AbstractTableModel implements Refreshable {

    private List<Game> games;

    public GameModel() {
        this.games = new ArrayList<>();
        MainFrame.addRefreshable(this);
    }

    public GameModel(Collection<Game> games) {
        this.games = new ArrayList<>(games);
    }

    public Game getGame(int row) {
        return games.get(row);
    }

    public Collection<Game> getGames(int[] rows) {
        ArrayList<Game> sbs = new ArrayList<>();
        for (int row : rows) {
            sbs.add(games.get(row));
        }
        return sbs;
    }

    @Override
    public void refresh() throws MyException {
        Collection<Game> rs = FacadeService.getDefault().getGames();
        games = new ArrayList<>(rs);
        Collections.sort(games, new Comparator<Game>() {
            @Override
            public int compare(Game t, Game t1) {
                return Collator.getInstance().compare(t.getName(), t1.getName());
            }
        });
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return games.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Game r = getGame(row);
        switch (col) {
            case 0:
                return r.getName();
            case 1:
                return r.getMap();
            case 2:
                return r.getPlayers();
            case 3:
                return r.getCapacity();
            default:
                assert false;
                return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return Name.cm();
            case 1:
                return Map.cm();
            case 2:
                return Players.cm();
            case 3:
                return Capacity.cm();
            default:
                assert false;
                return null;
        }
    }
}
