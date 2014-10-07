package controllers;

import play.*;
import play.mvc.*;

import java.io.File;
import java.util.*;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.IContainer;

import models.*;



public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void uploadVideo(File video, String outputtype)
    {
	String outputFilename = "public/output." + outputtype;
	IMediaReader reader = ToolFactory.makeReader(video.getPath());
	IMediaWriter writer = ToolFactory.makeWriter(outputFilename, reader);
	
	reader.addListener(writer);
	while (reader.readPacket() == null);
	renderArgs.put("file", "/" + outputFilename);
	render();
    }
}
