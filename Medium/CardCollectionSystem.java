import java.util.*;

public class CardCollectionSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        collection.addCard("Hearts", "Ace");
        collection.addCard("Hearts", "King");
        collection.addCard("Spades", "Queen");
        collection.addCard("Diamonds", "Jack");
        collection.addCard("Clubs", "10");
        
        System.out.println("Card Collection System");
        collection.displayCards();

        System.out.print("\nEnter a symbol to search for cards (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        List<String> cards = collection.getCardsBySymbol(symbol);
        
        if (cards.isEmpty()) {
            System.out.println("No cards found for symbol: " + symbol);
        } else {
            System.out.println("Cards for " + symbol + ": " + cards);
        }
    }
}

class CardCollection {
    private final HashMap<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String card) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }

    public void displayCards() {
        for (Map.Entry<String, List<String>> entry : cardMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

