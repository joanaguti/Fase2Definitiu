import Business.AdventureManager;
import Business.CharacterManager;
import Business.Dice;
import Business.PlayManager;
import Persistence.AdventuresJsonDAO;
import Persistence.CharactersJsonDAO;
import Persistence.MonstersJsonDAO;
import Presentation.MenuController;
import Presentation.Views.MenuView;

public class Main {
    public static void main(String[] args) {
        MenuView menu = new MenuView();
        try{
            CharactersJsonDAO charJsonDAO = new CharactersJsonDAO();
            //abans hi havia gson entre (), ho he tret.
            AdventuresJsonDAO advJsonDAO = new AdventuresJsonDAO();
            Dice dice = new Dice();

            MonstersJsonDAO monstJsonDAO = new MonstersJsonDAO();
            CharacterManager cm = new CharacterManager(charJsonDAO);
            AdventureManager am = new AdventureManager(advJsonDAO, monstJsonDAO);
            PlayManager pm = new PlayManager();
            MenuController mc = new MenuController(menu, cm, am, pm, dice);
            mc.run();
        }catch(Exception ex){   //Filenotfoud exepcion connectar daos
            menu.showMessage("Error: The monsters.json file can’t be accessed.");   //monsters o caracters
        }

    }
}