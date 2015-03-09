/**
 * 
 */
package com.dream.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author Frank
 *
 */
public class FileChannelTest {
	
	
	@Test
	public void testTransferFrom() throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile("from.txt","rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("to.txt","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0 ;
		long count = fromChannel.size();
		
		toChannel.transferFrom(fromChannel, position, count);
	}
	
	
	@Test
	public void testTransferTo() throws IOException{
		RandomAccessFile fromFile = new RandomAccessFile("from.txt","rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("to.txt","rw");
		FileChannel toChannel = toFile.getChannel();
		
		long position = 0 ;
		long count = fromChannel.size();
		
		fromChannel.transferTo(position, count,toChannel);
	}
	
	
	

}
