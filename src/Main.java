import Business.AdventureManager;
import Business.CharacterManager;
import Business.PlayManager;
import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;
import Presentation.MenuController;
import Presentation.Views.MenuView;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        MenuView menu = new MenuView();
        try{
            CharactersJsonDAO charJsonDAO = new CharactersJsonDAO();
            AdventuresJsonDAO advJsonDAO = new AdventuresJsonDAO();

            MonstersJsonDAO monstJsonDAO = new MonstersJsonDAO();
            CharacterManager cm = new CharacterManager(charJsonDAO);
            AdventureManager am = new AdventureManager(advJsonDAO, monstJsonDAO);
            PlayManager pm = new PlayManager();
            MenuController mc = new MenuController(menu, cm, am, pm);
            mc.run();
        }catch(Exception ex){   //Filenotfoud exepcion
            menu.showMessage("Error: The monsters.json file can’t be accessed.");
        }

    }
}