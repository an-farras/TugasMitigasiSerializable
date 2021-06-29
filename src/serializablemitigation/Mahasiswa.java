/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializablemitigation;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 *
 * @author Farras Ahmad
 */
public class Mahasiswa implements Serializable, Externalizable {
    private String nama;
    private int nim;
    
    private final Object lock = new Object();
    private boolean initialized = false;
    
    public Mahasiswa() {
        this.nama = null;
        this.nim = 0;
    }

    public Mahasiswa(int nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" + "nim=" + nim + ", nama=" + nama + '}';
    }
        
    public int getNim() {
        return nim;
    }

    public void setNim(int nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(nama);
        out.writeInt(nim);
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        synchronized(lock) {
            if(!initialized) {
                this.nama = (String) in.readObject();
                this.nim = in.readInt();
                initialized = true;
                System.out.println(this.toString());
            } else {
                throw new IllegalStateException();
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
        
        
}
