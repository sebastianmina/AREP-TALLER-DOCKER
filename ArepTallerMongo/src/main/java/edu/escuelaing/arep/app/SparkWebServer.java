package edu.escuelaing.arep.app;



import com.google.gson.Gson;
import static spark.Spark.*;

public class SparkWebServer {
    
	 public static void main(String... args)
	    {
	    	HttpClient cliente = new HttpClient();
	    	ConexionDB conexionDB = new ConexionDB();
	    	
	    	port(getPort());
	    	staticFileLocation("/static");
	    	get("/hello", (req,res) -> {
	    		res.redirect("index.html");
	    		return null;
	    	});
	    	
	        get("/resultados", (req, res) -> {
	        	res.status(200);
	        	res.type("application/json");
	        	String resp = cliente.getMensaje();
	        	cliente.cambiarNumeroServidor();
	        	return resp;
	        	});
	          
	        post("/resultados", (req, res) -> {
	        	cliente.postMensaje(req.body());
	        	cliente.cambiarNumeroServidor();
	        	return "";
	        	});
	        
	        get("/mensajes", (req, res) -> {
	            res.status(200);
	            res.type("application/json");
	            return new Gson().toJson(conexionDB.getMensaje());
	        });
	        
	        post("/mensajes", (req, res) -> {
	            Mensaje a = new Mensaje(req.body());
	            conexionDB.insertarMensaje(a);
	            return null;
	        });
	    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
    
}
