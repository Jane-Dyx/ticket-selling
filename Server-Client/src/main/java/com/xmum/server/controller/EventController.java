package com.xmum.server.controller;

import com.xmum.server.ServerApplication;
import com.xmum.server.cons.Constant;
import com.xmum.server.entity.Event;
import com.xmum.server.gui.MessageWindow;
import com.xmum.server.msg.Message;
import com.xmum.server.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/api/event")
    public List<Event> getAllEvents(){
        List<Event> events = null;
        try {
            events = eventService.getAllEvents().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        for(Event event:events){
//            FileInputStream fis = null;
//            try {
//                File file = new File(event.getImage());
//                byte[] bytes = new byte[Math.toIntExact(file.length())];
//                fis = new FileInputStream(event.getImage());
//                fis.read(bytes);
//                event.setImageData(bytes);
//                String[] splits = event.getImage().split("/");
//                String imageName = splits[splits.length - 1];
//                event.setImage(imageName);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        MessageWindow.getInstance().eventLog("All events have been retrieved just now.");
        return events;
    }

    @PostMapping(value = "/api/event")
    public Message createNewEvent(@RequestParam("title") String title,
                                 @RequestParam("details") String details,
                                 @RequestParam("start")Timestamp start,
//                                 @RequestParam("end") Timestamp end,
                                 @RequestParam("hid") Integer hid,
                                 @RequestParam("price1") BigDecimal price1,
                                 @RequestParam("price2") BigDecimal price2,
                                 @RequestParam("price3") BigDecimal price3
//                                 @RequestParam("image")MultipartFile image
    ) throws IOException {
        Event event = new Event();
        event.setTitle(title);
        event.setDetails(details);
        event.setStart(start);
//        event.setEnd(end);
        event.setHid(hid);
        event.setPrice1(price1);
        event.setPrice2(price2);
        event.setPrice3(price3);

        // create a folder for each image
        UUID uuid = UUID.randomUUID();
        File dir = new File(Constant.EVENT_IMAGE_FOLDER + uuid);
        dir.mkdir();
        // image path
//        String imagePath = dir + "/" + image.getOriginalFilename();
//        // save the image
//        image.transferTo(new File(imagePath));
//        event.setImage(imagePath);

        int result = eventService.createEvent(event);
        Message msg = new Message();
        if(result == 1){
            MessageWindow.getInstance().eventLog("Admin added new Event[" + event.getEid()+"].");
            msg.setCode(Message.CORRECT_CODE);
            msg.setMessage("Successfully added the event.");
        }else{
            MessageWindow.getInstance().eventLog("Admin tried to add new event but failed.");
            msg.setCode(Message.ERROR_CODE);
            msg.setMessage("Failed to add the event.");
        }
        return msg;
    }
}
