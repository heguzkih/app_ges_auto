package preferencias;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.ges_auto.modelo.Token;

public class GestorPreferencia {

    private static  final String  SHARED_PREFERENCES_TOKEN= "SHARED_PREFERENCES";
    private  String  SHARED_PREFERENCES_SERVIDOR= "SHARED_PREFERENCES";
    private  String error = "error";
    private  static  GestorPreferencia instance;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private  GestorPreferencia (Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_TOKEN, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static synchronized  GestorPreferencia getInstance(Context context){


            instance= new GestorPreferencia(context);

        return  instance;
    }


    public  void  saveToken (Token token){
        editor.putString(SHARED_PREFERENCES_TOKEN,token.getSucces());
        editor.apply();
    }

    public void saveServidor(String servidor){

        editor.putString(SHARED_PREFERENCES_SERVIDOR,servidor);
        editor.apply();
    }

    public  boolean isLoggin (){
        if(!sharedPreferences.getString(SHARED_PREFERENCES_TOKEN, error).equalsIgnoreCase(error)){
            return  true;
        }
        return false;

    }

    public Token getToken(){
        Token token = new Token();
        token.setSucces(sharedPreferences.getString(SHARED_PREFERENCES_TOKEN, error));

        return token;

    }

    public String getServidor(){
        String servidor = sharedPreferences.getString(SHARED_PREFERENCES_SERVIDOR,error);
        return  servidor;
    }





}
