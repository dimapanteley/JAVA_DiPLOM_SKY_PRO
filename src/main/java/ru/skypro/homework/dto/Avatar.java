package ru.skypro.homework.dto;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
public class Avatar {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String filePath;
        private long fileSize;
        private String mediaType;
        @Lob
        @Type(type = "org.hibernate.type.BinaryType")
        private byte[] data;
        @OneToOne
        private User user;
}
