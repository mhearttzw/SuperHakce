package com.superhakce.algorithm.designpatterns.decorator;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: heqingjiang
 * @Maintenance: author
 * @Description: 输出文件流装饰器
 * @Date: Create in 2018/10/4 21:18
 */
public class EncryptOutputStream extends OutputStream{

    private OutputStream outputStream;

    public EncryptOutputStream(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void write(int b) throws IOException{
        b += 2;
        if(b > (97 + 26))
            b -= 26;
        this.outputStream.write(b);
    }

    public static void main(String[] args) throws Exception{
        DataOutputStream dataOutputStream =
                new DataOutputStream(
                        new EncryptOutputStream(
                new BufferedOutputStream(

                new FileOutputStream("hqj.txt"))));
        dataOutputStream.write("abcdefgz".getBytes());
        dataOutputStream.close();
    }

}
