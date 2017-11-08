package axis.guren.com.cinemania.QuizType;

/**
 * Created by Guren on 5/4/2017.
 */
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Cinemania";
    // tasks table name
    private static final String TABLE_QUEST = "movies";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; //correct option
    private static final String KEY_OPTA= "opta"; //option a
    private static final String KEY_OPTB= "optb"; //option b
    private static final String KEY_OPTC= "optc"; //option c
    private SQLiteDatabase dbase;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
                +KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";
        db.execSQL(sql);
        addQuestions();
//db.close();
    }
    private void addQuestions()
    {
        Question q1=new Question("avengers",// movie title
                "Robert Downey Jr., Chris Evans, Scarlett Johannson, Chris Hemsworth, Mark Ruffalo, Jeremy Renner, Samuel L. Jackson", // cast
                "Earth's mightiest heroes must come together and learn to fight as a team if they are to stop the mischievous Loki and his alien army from enslaving humanity.", //synopsis
                "CISCO", //pending
                "avengers"); // answer
        this.addQuestion(q1);
        Question q2=new Question("frozen", // movie title
                "SuSe", // cast
                "BIOS", // synopsis
                "DOS", // pending
                "frozen"); // answer
        this.addQuestion(q2);

        Question q3=new Question("madmaxfuryroad",
                "Tom Hardy, Charlize Theron, Nicholas Hoult, Hugh Keays-Byrne, Josh Helman, Nathan Jones, Zoe Kravitz, Rosie Huntington-Whiteley",
                "A woman rebels against a tyrannical ruler in postapocalyptic Australia in search for her home-land with the help of a group of female prisoners, a psychotic worshipper, and a drifter named Max.",
                "Register",
                "mad max fury road");
        this.addQuestion(q3);

        Question q4=new Question("theconjuring",
                "Patrick Wilson, Vera Farmiga, Ron Livingston, Lili Taylor, Shanley Caswell, Hayley McFarland",
                "Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse.",
                "Hub",
                "the conjuring");
        this.addQuestion(q4);

        Question q5=new Question("spiderman",
                "Tobey Maguire, Kirsten Dunst, Willem Dafoe, James Franco, Cliff Robertson, Rosemary Harris, J.K. Simmons, Joe Manganiello",
                "When bitten by a genetically modified spider, a nerdy, shy, and awkward high school student gains spider-like abilities that he eventually must use to fight evil as a superhero after tragedy befalls his family.",
                "BASIC",
                "spiderman");
        this.addQuestion(q5);

        Question q6=new Question("starwarstheforceawakens",
                "Daisy Ridley, John Boyega, Oscar Isaac, Adam Driver, Lupita Nyong'o, Domnhall Gleeson, Andy Serkis, Harrison Ford, Carrie Fisher",
                "Three decades after the defeat of the Galactic Empire, a new threat arises. The First Order attempts to rule the galaxy and only a ragtag group of heroes can stop them, along with the help of the Resistance.",
                "BASIC",
                "star wars the force awakens");
        this.addQuestion(q6);

        Question q7=new Question("megamind",
                "Will Ferrell, Tina Fey,Jonah Hill, Brad Pitt, David Cross, Ben Stiller, Justin Theroux, Jessica Schulte",
                "A blue-skinned supervillain with a big head finally defeats his nemesis, the superhero Metro Man. But without a hero, he loses all purpose and must find new meaning to his life.",
                "BASIC",
                "megamind");
        this.addQuestion(q7);

        Question q8=new Question("avatar",
                "Sam Worthington, Zoe Saldana, Sigourney Weaver, Stephen Lang, Michelle Rodriguez,  Giovanni Ribisi,  Joel David Moore",
                "A paraplegic marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                "BASIC",
                "avatar");
        this.addQuestion(q8);

        Question q9=new Question("worldwarz",
                "Brad Pitt, Mireille Enos, Daniella Kertesz, James Badge Dale, Ludi Boeken, Matthew Fox, David Morse",
                "Former United Nations employee Gerry Lane traverses the world in a race against time to stop the Zombie pandemic that is toppling armies and governments, and threatening to destroy humanity itself.",
                "BASIC",
                "world war z");
        this.addQuestion(q9);

        Question q10=new Question("thejunglebook2016",
                "Neel Sethi, Bill Murray, Ben Kingsley, Idris Elba, Lupita Nyong'o, Scarlett Johansson, Giancarlo Esposito, Christopher Walken",
                "After a threat from the tiger Shere Khan forces him to flee the jungle, a man-cub named Mowgli embarks on a journey of self discovery with the help of panther, Bagheera, and free spirited bear, Baloo.",
                "BASIC",
                "the jungle book");
        this.addQuestion(q10);

        Question q11=new Question("insideout",
                "Amy Poehler, Bill Hader, Lewis Black, Phyllis Smith, Mindy Kaling, Kaitlyn Dias, Richard Kind, Diane Lane, Kyle MacLachlan",
                "After young Riley is uprooted from her Midwest life and moved to San Francisco, her emotions - Joy, Fear, Anger, Disgust and Sadness - conflict on how best to navigate a new city, house, and school.",
                "BASIC",
                "inside out");
        this.addQuestion(q11);

        Question q12=new Question("suicidesquad",
                "Will Smith, Jared Leto, Margot Robbie, Viola Davis, Joel Kinnaman, Cara Delevingne, Jai Courtney, Jay Hernandez, Adewale Akinnuoye-Agbaje,  Karen Fukuhara,  Adam Beach",
                "A secret government agency recruits some of the most dangerous incarcerated super-villains to form a defensive task force. Their first mission: save the world from the apocalypse.",
                "BASIC",
                "suicide squad");
        this.addQuestion(q12);

        Question q13=new Question("harrypotterandthechamberofsecrets",
                "Daniel Radcliffe, Rupert Grint, Emma Watson, Toby Jones, Kenneth Branagh, Christian Coulson, Richard Harris, Alan Rickman",
                "Harry ignores warnings not to return to Hogwarts, only to find the school plagued by a series of mysterious attacks and a strange voice haunting him.",
                "BASIC",
                "harry potter and the chamber of secrets");
        this.addQuestion(q13);

        Question q14=new Question("thering",
                "Naomi Watts, Martin Henderson, Brian Cox, David Dorfman, Jane Alexander, Daveigh Chase",
                "A journalist must investigate a mysterious videotape which seems to cause the death of anyone in a week of viewing it.",
                "BASIC",
                "the ring");
        this.addQuestion(q14);

        Question q15=new Question("deadpool",
                "Ryan Reynolds, Morena Baccarin, T.J. Miller, Ed Skrein, Stefan Kapicic, Brianna Hildebrand, Leslie Uggams, Karan Soni",
                "A fast-talking mercenary with a morbid sense of humor is subjected to a rogue experiment that leaves him with accelerated healing powers and a quest for revenge.",
                "BASIC",
                "deadpool");
        this.addQuestion(q15);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
            // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
            //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());
            // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
            // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
            // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
