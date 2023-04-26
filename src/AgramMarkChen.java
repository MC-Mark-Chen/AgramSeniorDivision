import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AgramMarkChen 
{
    /**
     * This method prints the card the dealer must play according to the strategy listed below for each input line.
     *      1. The dealer must play a card of the same suit if he can.
     *      2. He plays the lowest card in that suit that is of a higher rank than the card the opponent played.
     *      3. If he does not have such a card, he plays his lowest card in that suit.
     *      4. If he does not have a card in that suit, he plays the lowest ranking card regardless of suit.
     *      5. If there is a tie, he plays the card in the following order: clubs, diamonds, hearts and spades.
     * 
     * @param cardLead string representing the card opponent draws
     * @param card1 string representing the first card available for the dealer 
     * @param card2 string representing the second card available for the dealer 
     * @param card3 string representing the third card available for the dealer 
     * @param card4 string representing the fourth card available for the dealer 
     * @param card5 string representing the fifth card available for the dealer 
     */
    public static void agram(String cardLead, String card1, String card2, String card3, String card4, String card5)
    {
        final String RANKS = "A23456789TJQK";                   //order of the ranks
        final String TIE_RANKS = "CDHS";                    //order of the ranks when there is a tie

        String designatedSuit = cardLead.substring(1);                  //the desire suit given by the opponent's card
        int designatedRank = RANKS.indexOf(cardLead.substring(0, 1)) + 1;                   //the desire rank given by the opponent's card

        String[] cards = {card1, card2, card3, card4, card5};
        ArrayList<Integer> index = new ArrayList<Integer>();
        ArrayList<String> tie = new ArrayList<String>();
        ArrayList<Integer> tieValues = new ArrayList<Integer>();

        int min = Integer.MAX_VALUE;
        int tieMin = Integer.MAX_VALUE;

        for(int i = 0; i < cards.length; i++)
        {
            if(cards[i].substring(1).equals(designatedSuit))
            {
                index.add(RANKS.indexOf(cards[i].substring(0, 1)) + 1);                 //add the ranks of the cards that match the desire suit
            }
        }

        if(index.size() > 0)                    //if there is at least one card mathches the desire suit
        {
            for(int i = 0; i < index.size(); i++)
            {
                if(index.get(i) > designatedRank)
                {
                    min = Math.min(min, index.get(i));                  //get the minimum rank of the cards that is higher than the opponent's rank
                }
            }

            if(min == Integer.MAX_VALUE)                    //if all the cards in the desire suit have a lower rank than the opponent
            {
                for(int i = 0; i < index.size(); i++)
                {
                    min = Math.min(min, index.get(i));                  //get the minimum rank of all cards in the desire suit
                }
            }

            System.out.println(RANKS.substring(min - 1, min) + designatedSuit);                 //print the minimum rank with the desire suit
        }

        else                    //if none of the cards matches the desire suit
        {
            for(int i = 0; i < cards.length; i++)
            {
                min = Math.min(min, RANKS.indexOf(cards[i].substring(0, 1)) + 1);                   //get the minimum rank of out of all cards
            }

            for(int i = 0; i < cards.length; i++)
            {
                if(RANKS.indexOf(cards[i].substring(0, 1)) + 1 == min)
                {
                    tie.add(cards[i]);                  //add all the cards that have a tie
                }
            }

            for(int i = 0; i < tie.size(); i++)
            {
                tieValues.add(TIE_RANKS.indexOf(tie.get(i).substring(1)));                  //add the index of the suits of the cards in tie
            }

            for(int i = 0; i < tieValues.size(); i++)
            {
                tieMin = Math.min(tieMin, tieValues.get(i));                    //get the minimum index of suits out of all tied cards
            }

            System.out.println(tie.get(tieValues.indexOf(tieMin)));                 //each card that is in tie has the same index in ArrayList tie and tieValues, so we can use the index of tieMin in tieValues to track back the desire card in tie
        }
    }

    /**
     * Reads each line and calls agram method
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("/Users/mark/Library/CloudStorage/OneDrive-个人/AP CSA/Coding Assignments/Agram SNR input.txt");
        Scanner scan = new Scanner(file);

        while(scan.hasNext())
        {
            String cardLead = scan.next();
            String card1 = scan.next();
            String card2 = scan.next();
            String card3 = scan.next();
            String card4 = scan.next();
            String card5 = scan.next();

            agram(cardLead, card1, card2, card3, card4, card5);
        }
    }
}