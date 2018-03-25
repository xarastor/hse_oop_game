package Controller;

import Item.ItemStorage;
import Manager.GameManager;
import Map.Cell;

import java.io.IOException;
import java.lang.Character;

public class ConsoleController implements IController {
    public final int DefaultMenu = -1;  // I
    public final int HelmetMenu = 0;    // H
    public final int WeaponMenu = 1;    // W
    public final int BootsMenu = 2;     // T
    public final int GlovesMenu = 3;    // G
    public final int ArmorMenu = 4;     // A
    public final int PantsMenu = 5;     // P

    public String ReadFromConsole() {
        try {
            String res = "";
            char symbol =(char)System.in.read();
            res += symbol;
            char ignore;
            do {
                ignore = (char) System.in.read();
                if (ignore != '\n')
                    res += ignore;
            } while(ignore != '\n');
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }

    public void WaitForRead() {
        String readed = ReadFromConsole();
        HandleRead(readed);
    }

    public void HandleRead(String inputString) {
        char symbol = inputString.charAt(0);
        if (GameManager.getInstance().isWaitingForInventoryId) {
            try {
                int ItemId = Integer.parseInt(inputString);
                if (GameManager.getInstance().player.getInventory().Equip(ItemId)) {
                    GameManager.getInstance().renderer.ItemEquipped();
                } else {
                    GameManager.getInstance().renderer.ItemNotInInventory();
                }
            } catch (java.lang.NumberFormatException e) {
                GameManager.getInstance().renderer.WrongIntegerInput();
            }
            GameManager.getInstance().EquippedEnd();
            GameManager.getInstance().SelectInventoryCategory(DefaultMenu);
            GameManager.getInstance().renderer.ShowInventory();

        } else {
            switch (Character.toUpperCase(symbol)) {
                case 'S': {
                    if (!GameManager.getInstance().isGameStarted) {
                        GameManager.getInstance().Start();
                    } else {
                        if (GameManager.getInstance().inMapMenu) {
                            GameManager.getInstance().GoDown();
                            GameManager.getInstance().ShowMap();
                        } else {
                            if (GameManager.getInstance().inCharacterMenu) {
                                GameManager.getInstance().StrengthUp();
                                GameManager.getInstance().renderer.ShowCharacterMenu();
                            } else {
                                GameManager.getInstance().renderer.WrongInput();
                            }
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
                case 'C': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inMainMenu) {
                            GameManager.getInstance().ShowCharacterMenu();
                        } else {
                            GameManager.getInstance().renderer.WrongInput();
                        }
                    } else {
                        GameManager.getInstance().renderer.GameNotStarted();
                    }
                    break;
                }
                case 'I': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inMainMenu) {
                            GameManager.getInstance().ShowInventory();
                        } else {
                            if (GameManager.getInstance().inInventoryMenu) {
                                GameManager.getInstance().SelectInventoryCategory(DefaultMenu);
                                GameManager.getInstance().ShowInventory();
                            } else {
                                if (GameManager.getInstance().inCharacterMenu) {
                                    GameManager.getInstance().IntelligenceUp();
                                    GameManager.getInstance().renderer.ShowCharacterMenu();
                                } else {
                                    GameManager.getInstance().renderer.WrongInput();
                                }
                            }
                        }
                    } else {
                        GameManager.getInstance().renderer.GameNotStarted();
                    }
                    break;
                }
                case 'B': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (    GameManager.getInstance().inMapMenu ||
                                GameManager.getInstance().inInventoryMenu ||
                                GameManager.getInstance().inCharacterMenu) {
                            GameManager.getInstance().ShowMainMenu();
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
                    return;
                }
                case 'W': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inMapMenu) {
                            GameManager.getInstance().GoUp();
                            GameManager.getInstance().ShowMap();
                        } else {
                            if (GameManager.getInstance().inInventoryMenu) {
                                GameManager.getInstance().SelectInventoryCategory(WeaponMenu);
                                GameManager.getInstance().ShowInventory();
                            } else {
                                if (GameManager.getInstance().inCharacterMenu) {
                                    GameManager.getInstance().WisdomUp();
                                    GameManager.getInstance().renderer.ShowCharacterMenu();
                                } else {
                                    GameManager.getInstance().renderer.WrongInput();
                                }
                            }
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
                            if (GameManager.getInstance().inInventoryMenu) {
                                GameManager.getInstance().SelectInventoryCategory(ArmorMenu);
                                GameManager.getInstance().ShowInventory();
                            } else {
                                if (GameManager.getInstance().inCharacterMenu) {
                                    GameManager.getInstance().AgilityUp();
                                    GameManager.getInstance().renderer.ShowCharacterMenu();
                                } else {
                                    GameManager.getInstance().renderer.WrongInput();
                                }
                            }
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
                case 'P': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inMainMenu || GameManager.getInstance().inMapMenu) {
                            if (GameManager.getInstance().getCurrentCell().Type == Map.CellType.Artifact) {
                                for (Integer itemId : GameManager.getInstance().getCurrentCell().Artifacts) {
                                    ItemStorage.Items.get(itemId).pickup();
                                }
                                GameManager.getInstance().getCurrentCell().Type = Map.CellType.Empty;
                                GameManager.getInstance().renderer.ItemsPickedUp(GameManager.getInstance().getCurrentCell().Artifacts);
                            }
                        } else {
                            if (GameManager.getInstance().inInventoryMenu) {
                                GameManager.getInstance().SelectInventoryCategory(PantsMenu);
                                GameManager.getInstance().ShowInventory();
                            } else {
                                GameManager.getInstance().renderer.WrongInput();
                            }
                        }
                    } else {
                        GameManager.getInstance().renderer.GameNotStarted();
                    }
                    break;
                }
                case 'H': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inInventoryMenu) {
                            GameManager.getInstance().SelectInventoryCategory(HelmetMenu);
                            GameManager.getInstance().ShowInventory();
                        } else {
                            GameManager.getInstance().renderer.WrongInput();
                        }

                    } else {
                        GameManager.getInstance().renderer.GameNotStarted();
                    }
                    break;
                }
                case 'T': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inInventoryMenu) {
                            GameManager.getInstance().SelectInventoryCategory(BootsMenu);
                            GameManager.getInstance().ShowInventory();
                        } else {
                            GameManager.getInstance().renderer.WrongInput();
                        }

                    } else {
                        GameManager.getInstance().renderer.GameNotStarted();
                    }
                    break;
                }
                case 'G': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inInventoryMenu) {
                            GameManager.getInstance().SelectInventoryCategory(GlovesMenu);
                            GameManager.getInstance().ShowInventory();
                        } else {
                            GameManager.getInstance().renderer.WrongInput();
                        }

                    } else {
                        GameManager.getInstance().renderer.GameNotStarted();
                    }
                    break;
                }
                case 'Q': {
                    if (GameManager.getInstance().isGameStarted) {
                        if (GameManager.getInstance().inInventoryMenu) {
                            GameManager.getInstance().TryToEquipItem();
                            GameManager.getInstance().renderer.ShowEquipHelp();
                        } else {
                            GameManager.getInstance().renderer.WrongInput();
                        }

                    } else {
                        GameManager.getInstance().renderer.GameNotStarted();
                    }
                    break;
                }
                case 'X': {
                    if (GameManager.getInstance().isGameStarted) {
                        GameManager.getInstance().Cheat();
                    }
                    break;
                }
                default: {
                    GameManager.getInstance().renderer.WrongInput();
                    break;
                }
            }
        }
        if (!GameManager.getInstance().isWaitingForInventoryId) {
            GameManager.getInstance().renderer.ShowCurrentHelp();
        }
    }

    public void MakeGameLoop() {
        while(GameManager.getInstance().isValidGame) {
            WaitForRead();
        }
        System.exit(0);
    }
}
