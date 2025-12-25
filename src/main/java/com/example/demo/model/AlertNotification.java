package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert_notifications")
public class AlertNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private VisitLog visitLog;

    private String sentTo;
    private String alertMessage;
    private LocalDateTime sentAt = LocalDateTime.now();

    public AlertNotification() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VisitLog getVisitLog() { return visitLog; }
    public void setVisitLog(VisitLog visitLog) { this.visitLog = visitLog; }

    public String getSentTo() { return sentTo; }
    public void setSentTo(String sentTo) { this.sentTo = sentTo; }

    public String getAlertMessage() { return alertMessage; }
    public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }

    public LocalDateTime getSentAt() { return sentAt; }

    // 🔴 THIS WAS MISSING (TEST REQUIRES IT)
    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}




// package com.example.demo.model;

// import jakarta.persistence.*;
// import java.time.LocalDateTime;

// @Entity
// @Table(name = "alert_notifications")
// public class AlertNotification {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToOne private VisitLog visitLog;

//     private String sentTo;
//     private String alertMessage;
//     private LocalDateTime sentAt = LocalDateTime.now();

//     public AlertNotification() {}

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public VisitLog getVisitLog() { return visitLog; }
//     public void setVisitLog(VisitLog visitLog) { this.visitLog = visitLog; }

//     public String getSentTo() { return sentTo; }
//     public void setSentTo(String sentTo) { this.sentTo = sentTo; }

//     public String getAlertMessage() { return alertMessage; }
//     public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }

//     public LocalDateTime getSentAt() { return sentAt; }
// }




// package com.example.demo.model;
// import jakarta.persistence.Entity;
// import java.time.LocalDateTime;

// public class AlertNotification {

//     private Long id;
//     private VisitLog visitLog;
//     private String sentTo;
//     private String alertMessage;
//     private LocalDateTime sentAt;

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public VisitLog getVisitLog() { return visitLog; }
//     public void setVisitLog(VisitLog visitLog) { this.visitLog = visitLog; }

//     public String getSentTo() { return sentTo; }
//     public void setSentTo(String sentTo) { this.sentTo = sentTo; }

//     public String getAlertMessage() { return alertMessage; }
//     public void setAlertMessage(String alertMessage) { this.alertMessage = alertMessage; }

//     public LocalDateTime getSentAt() { return sentAt; }
//     public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
// }
