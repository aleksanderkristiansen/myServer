package endpoints; /**
 * Created by mortenlaursen on 09/10/2016.
 */

import Encrypters.Crypter;
import com.google.gson.Gson;
import controllers.BookController;
import controllers.TokenController;
import model.Book;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// The Java class will be hosted at the URI path "/Book"

/**
 * Endpoint klasse indeholder alle bog-endpoints. Klassen står for kald af metoder fra controller-klasse
 * og kommunikation med klient.
 */

@Path("/book")
public class BookEndpoint {

    BookController controller = new BookController();
    TokenController tokenController = new TokenController();

    public BookEndpoint() {
    }

    // The Java method will process HTTP GET requests

    /**
     * Denne metode gør det muligt for klienten at hente en liste over bøger.
     * Dataen, der bliver vist for klienten er krypteret jf. encryptDecryptXOR-metoden i Crypter-klassen
     * @return Metoden returner enten en status 200 eller 400, alt afhængig af om forespørgslen bliver godkendt
     * af serveren. Hertil følger også en fejlmeddelelse, hvis metoden returner en status 400.
     * @throws Exception
     */
    @GET
    @Produces("application/json")
    public Response get() throws Exception {

        if (controller.getBooks()!=null) {

            String lstBooks = new Gson().toJson(controller.getBooks());

            String lstBookCrypted = Crypter.encryptDecryptXOR(lstBooks);

            return Response
                    .status(200)
                    .entity(lstBookCrypted)
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    /**
     * Denne metode bruges til at hente data om en enkelt bog.
     * @param bookId metoden bruger bookId som parameter fra klienten, til at vide hvilken bog, der skal vises.
     * @return Ligesom tidligere metode returner denne også enten status 200 eller 400.
     * @throws Exception
     */
    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Response get(@PathParam("id") int bookId) throws Exception {
        if (controller.getBook(bookId)!=null) {

            String getBook = new Gson().toJson(controller.getBook(bookId));

            String getBookCrypted = Crypter.encryptDecryptXOR(getBook);

            return Response
                    .status(200)
                    .entity(getBookCrypted)
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    @POST
    @Produces("application/json")
    public Response create(String bookData) throws Exception {
        String decryptBook = Crypter.encryptDecryptXOR(bookData);
        Book book = new Gson().fromJson(decryptBook, Book.class);
        if (controller.addBook(book)) {
            return Response
                    .status(200)
                    .entity("{\"message\":\"Success! Book created\"}")
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    /**
     * Gør det muligt for klienten at slette eksisterende bøger
     * @param authToken Tjekker om klienten sender en access-token med request.
     * @param bookId Modtager ID om bog, der skal slettes
     * @return Returner status 200, hvis bog er slettet. Ellers status 400 med fejlbesked.
     * @throws Exception
     */
    @Path("/{id}")
    @DELETE
    public Response delete (@HeaderParam("authorization") String authToken, @PathParam("id") int bookId) throws Exception {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){

            if(controller.deleteBook(bookId)) {
                return Response.status(200).entity("{\"message\":\"Success! Book deleted\"}").build();
            }
            else return Response.status(400).entity("{\"message\":\"failed\"}").build();
        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();
    }

    /**
     * Denne metode gør det muligt for klienten at hente en liste over forfatter.
     * Dataen, der bliver vist for klienten er krypteret jf. encryptDecryptXOR-metoden i Crypter-klassen
     * @return Metoden returner enten en status 200 eller 400, alt afhængig af om forespørgslen bliver godkendt
     * af serveren. Hertil følger også en fejlmeddelelse, hvis metoden returner en status 400.
     * @throws Exception
     */
    @Path("/authors")
    @Produces("application/json")
    @GET
    public Response getAuthors() throws Exception {
        if (controller.getAuthors()!=null) {


            String lstAuthors = new Gson().toJson(controller.getAuthors());

            String lstAuthorsCrypted = Crypter.encryptDecryptXOR(lstAuthors);

            return Response
                    .status(200)
                    .entity(lstAuthorsCrypted)
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    @Path("/publishers")
    @Produces("application/json")
    @GET
    public Response getPublishers() throws Exception {
        if (controller.getPublishers()!=null) {


            String lstPublishers = new Gson().toJson(controller.getPublishers());

            String lstPublishersCrypted = Crypter.encryptDecryptXOR(lstPublishers);

            return Response
                    .status(200)
                    .entity(lstPublishersCrypted)
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }

    @Path("/bookstores")
    @Produces("application/json")
    @GET
    public Response getBookStores() throws Exception {
        if (controller.getBookStores()!=null) {


            String lstBookStores = new Gson().toJson(controller.getBookStores());

            String lstBookStoresCrypted = Crypter.encryptDecryptXOR(lstBookStores);

            return Response
                    .status(200)
                    .entity(lstBookStoresCrypted)
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();
        }
    }
}

