import Business.AdventureManager;
import Business.CharacterManager;
import Business.DiceRoller;
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
            DiceRoller dice = new DiceRoller();

            MonstersJsonDAO monstJsonDAO = new MonstersJsonDAO();
            CharacterManager cm = new CharacterManager(charJsonDAO);
            AdventureManager am = new AdventureManager(advJsonDAO, monstJsonDAO);
            PlayManager pm = new PlayManager();
            MenuController mc = new MenuController(menu, cm, am, pm);
            mc.run();
        }catch(Exception ex){
            menu.showMessage("ERROR GENERAL MAIN");
        }

    }
}