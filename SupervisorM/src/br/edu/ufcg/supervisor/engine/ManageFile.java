/**
 * Copyright 2013-2014 Marcos Ferreira and Elthon Oliveira
 * 
 * This file is part of Supervisor for Healthcare Professional software.
 * 
 *  Supervisor for Healthcare Professional is free software: you can 
 *  redistribute it and/or modify it under the terms of the GNU General 
 *  Public License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 *  
 *  Supervisor for Healthcare Professional is distributed in the hope that
 *  it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 *  the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with Supervisor for Healthcare Professional. 
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package br.edu.ufcg.supervisor.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

public class ManageFile {
    private Context context;
    private boolean sdCardAvailable;
    private boolean sdCardWritableReadable;
    private boolean sdCardReadableOnly;
    
    public ManageFile(Context context){ this.context = context; }

    /**
     * Escreve no arquivo texto.
     * @param text Texto a ser escrito.
     * @return True se o texto foi escrito com sucesso.
     */
    public boolean WriteFile(String text){
        try {
            File file = new File(context.getExternalFilesDir(null), "romar.txt");
            FileOutputStream out = new FileOutputStream(file, true);
            out.write(text.getBytes());
            out.write("\n".getBytes());
            out.flush();
            out.close();    
            return true;
        } catch (Exception e) { return false; }
    }
    
    /**
     * Faz a leitura do arquivo
     * @return O texto lido.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String ReadFile() throws FileNotFoundException, IOException{
        File textfile = new File(context.getExternalFilesDir(null), "romar.txt");
        FileInputStream input = new FileInputStream(textfile);
        byte[] buffer = new byte[(int)textfile.length()];
        input.read(buffer);            
        input.close();
        return new String(buffer);
    }
    
    public String getStateSDcard(){
        // Obtêm o status do cartão SD
        String status = Environment.getExternalStorageState();
        
        if (Environment.MEDIA_BAD_REMOVAL.equals(status)) {
            // Midia foi removida antes de ser montada
            sdCardAvailable = false;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia removida.";
        }
        else if (Environment.MEDIA_CHECKING.equals(status)) {
            // Midia está presente e está sendo feita a verificação
            sdCardAvailable = true;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia sendo verificada.";
        }
        else if (Environment.MEDIA_MOUNTED.equals(status)) {
            // A midia está presente e montada neste momento com
            // permissão de escrita e leitura
            sdCardAvailable = true;
            sdCardWritableReadable = true;
            sdCardReadableOnly = false;
            return "Midia com permissão de escrita e leitura.";
        }
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(status)) {
            // A midia está presente e montada neste momento com 
            // permissão somente de leitura
            sdCardAvailable = true;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia com permissão somente leitura.";
        }
        else if (Environment.MEDIA_NOFS.equals(status)) {
            // A midia está presente, mas está vazia ou utilizando um
            // sistema de arquivos não suportado    
            sdCardAvailable = false;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia com sistema de arquivos não compatível.";
        }
        else if (Environment.MEDIA_REMOVED.equals(status)) {
            // A midia não está presente
            sdCardAvailable = false;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia não presente.";
        }
        else if (Environment.MEDIA_SHARED.equals(status)) {
            // A midia está presente, não montada e compartilhada 
            // via USB
            sdCardAvailable = false;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia compartilhada via USB.";
        }
        else if (Environment.MEDIA_UNMOUNTABLE.equals(status)) {
            // A midia está presente mas não pode ser montada
            sdCardAvailable = false;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia não pode ser montada";
        }
        else if (Environment.MEDIA_UNMOUNTED.equals(status)) {
            // A midia está presente mas não montada
            sdCardAvailable = false;
            sdCardWritableReadable = false;
            sdCardReadableOnly = false;
            return "Midia não montada.";
        }
        return "nao sei o que houve!";
    }

    public boolean isSdCardAvailable() {
        return sdCardAvailable;
    }

    public void setSdCardAvailable(boolean sdCardAvailable) {
        this.sdCardAvailable = sdCardAvailable;
    }

    public boolean isSdCardWritableReadable() {
        return sdCardWritableReadable;
    }

    public void setSdCardWritableReadable(boolean sdCardWritableReadable) {
        this.sdCardWritableReadable = sdCardWritableReadable;
    }

    public boolean isSdCardReadableOnly() {
        return sdCardReadableOnly;
    }

    public void setSdCardReadableOnly(boolean sdCardReadableOnly) {
        this.sdCardReadableOnly = sdCardReadableOnly;
    }
}