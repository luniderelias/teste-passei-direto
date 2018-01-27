package direto.passei.teste.passeidireto.Util;

import static com.j256.ormlite.android.apptools.OrmLiteConfigUtil.writeConfigFile;

/**
 * Created by lunid on 25/01/2018.
 */

public class DatabaseConfigUtil {
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}
