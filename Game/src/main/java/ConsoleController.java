import java.io.IOException;

public class ConsoleController implements IController {

    public char ReadFromConsole() {
        try {
            char symbol =(char)System.in.read();
            char ignore;
            do {
                ignore = (char) System.in.read();
            } while(ignore != '\n');
            return symbol;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (char)0;
    }

    public void WaitForRead() {
        char readed = ReadFromConsole();
        HandleRead(readed);
    }

    public void HandleRead(char symbol) {
        switch (symbol) {
            case 'S': {
                if (!GameManager.getInstance().isGameStarted) {
                    GameManager.getInstance().Start();
                } else {
                    if (GameManager.getInstance().inMapMenu) {
                        GameManager.getInstance().GoDown();
                        GameManager.getInstance().ShowMap();
                    } else {
                        GameManager.getInstance().renderer.WrongInput();
                    }
                }
                break;
            }
            case 'M': {
                if (GameManager.getInstance().isGameStarted) {
                    if (GameManager.getInstance().inMainMenu) {
                        GameManager.getInstance().ShowMap();
                    } else {
                        GameManager.getInstance().renderer.WrongInput();
                    }
                } else {
                    GameManager.getInstance().renderer.GameNotStarted();
                }
                break;
            }
            case 'E': {
                GameManager.getInstance().EndGame();
                break;
            }
            case 'W': {
                if (GameManager.getInstance().isGameStarted) {
                    if (GameManager.getInstance().inMapMenu) {
                        GameManager.getInstance().GoUp();
                        GameManager.getInstance().ShowMap();
                    } else {
                        GameManager.getInstance().renderer.WrongInput();
                    }
                } else {
                    GameManager.getInstance().renderer.GameNotStarted();
                }
                break;
            }
            case 'A': {
                if (GameManager.getInstance().isGameStarted) {
                    if (GameManager.getInstance().inMapMenu) {
                        GameManager.getInstance().GoLeft();
                        GameManager.getInstance().ShowMap();
                    } else {
                        GameManager.getInstance().renderer.WrongInput();
                    }
                } else {
                    GameManager.getInstance().renderer.GameNotStarted();
                }
                break;

            }
            case 'D': {
                if (GameManager.getInstance().isGameStarted) {
                    if (GameManager.getInstance().inMapMenu) {
                        GameManager.getInstance().GoRight();
                        GameManager.getInstance().ShowMap();
                    } else {
                        GameManager.getInstance().renderer.WrongInput();
                    }
                } else {
                    GameManager.getInstance().renderer.GameNotStarted();
                }
                break;
            }
            default:{
                GameManager.getInstance().renderer.WrongInput();
                break;
            }
        }
        GameManager.getInstance().renderer.ShowCurrentHelp();
    }

    public void MakeGameLoop() {
        while(GameManager.getInstance().isValidGame) {
            WaitForRead();
        }
        System.exit(0);
    }
}
