/**
 * Created by mortenlaursen on 09/10/2016.
 */

import com.google.gson.Gson;
import config.Config;
import config.ConfigMap;
import endpoints.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
public class MyApplication extends Application {

  private void initConfig() {

    Gson gson = new Gson();
    BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/config.json")));
    ConfigMap config = gson.fromJson(br, ConfigMap.class);

    Config.setDbName(config.getDbName());
    Config.setDbPassword(config.getDbPassword());
    Config.setDbPort(config.getDbPort());
    Config.setDbUrl(config.getDbUrl());
    Config.setDbUserName(config.getDbUserName());

  }

  //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
  @Override
  public Set<Class<?>> getClasses() {

    this.initConfig();

    HashSet h = new HashSet<Class<?>>();
    h.add(UsersEndpoint.class);
    h.add(BookEndpoint.class);
    h.add(CurriculumEndpoint.class);
    return h;
  }


}

