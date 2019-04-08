package kr.hs.dgsw.web_2_0326.Controller;

import kr.hs.dgsw.web_2_0326.Domain.User;
import kr.hs.dgsw.web_2_0326.Protocol.AttechmentProtocol;
import kr.hs.dgsw.web_2_0326.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserRepository usesRep;

    @PostMapping("/attachment")
    public AttechmentProtocol upload(@RequestPart MultipartFile file){
        String destFilename = "D:/DEV/web_2_0326/upload/" + UUID.randomUUID().toString() + " " + file.getOriginalFilename();
        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);
            return new AttechmentProtocol(destFilename, file.getOriginalFilename());
        }catch (Exception e){
            return null;
        }
    }

    @PutMapping("/uploadfile/{id}")
    public User uploadfile(@PathVariable Long id,@RequestBody User user){
        try {

            return this.usesRep.findById(id)
                    .map(found->{
                        found.setPath(Optional.ofNullable(user.getPath()).orElse(found.getPath()));
                        found.setImgname(Optional.ofNullable(user.getImgname()).orElse(found.getImgname()));
                        return usesRep.save(found);
                    })
                    .orElse(null);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/download/{path}/{id}")
    public void download(@PathVariable String path,@PathVariable Long id, HttpServletRequest req, HttpServletResponse resp){
        Optional<User> found = this.usesRep.findById(id);
        try{
            String filepath = found.get().getPath();
            String filename = found.get().getImgname();
            File file =new File(filepath);
            if(file.exists() == false) return;
            String fileType = URLConnection.guessContentTypeFromName(file.getName());
            if(fileType == null) fileType = "application/octet-stream";
            resp.setContentType(fileType);
            resp.setHeader("Content-Disposition", "inline; filename=\'" + filename + "\'");
            resp.setContentLength((int)file.length());
            InputStream ip = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(ip,resp.getOutputStream());
        }catch( Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}