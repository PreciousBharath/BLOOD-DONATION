<div align="center">
<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>

<img src="https://readme-typing-svg.demolab.com?font=Poppins&weight=800&size=40&duration=3500&pause=1500&color=ef473a&center=true&vCenter=true&width=900&height=70&lines=%F0%9F%A9%B8%20Blood%20Donation%20System" alt="Blood Donation System"/>

<b><i>Connecting donors, patients & admins — Java Web App</i></b>

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=20&pause=1000&color=36BCF7&center=true&vCenter=true&width=700&lines=%F0%9F%A9%B8%20Donate%20blood%2C%20save%20lives;%E2%98%95%20Java%20Servlets%20%2B%20JSP%20%2B%20MySQL;%F0%9F%8F%A5%20Donor%20%C2%B7%20Patient%20%C2%B7%20Admin%20portals" alt="Typing SVG" />

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/> <img src="https://img.shields.io/badge/Servlets_%26_JSP-007396?style=for-the-badge&logo=openjdk&logoColor=white" alt="Servlets/JSP"/> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/> <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/> <img src="https://img.shields.io/badge/Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white" alt="Tomcat"/> <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5"/> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3"/>

<img src="https://img.shields.io/github/stars/PreciousBharath/BLOOD-DONATION?style=social" alt="stars"/> <img src="https://img.shields.io/github/forks/PreciousBharath/BLOOD-DONATION?style=social" alt="forks"/> <img src="https://img.shields.io/github/last-commit/PreciousBharath/BLOOD-DONATION?style=flat-square&color=blueviolet" alt="last commit"/> <img src="https://img.shields.io/github/repo-size/PreciousBharath/BLOOD-DONATION?style=flat-square&color=success" alt="repo size"/>
</div>

---

## 🩸 About the Project

A full-featured **Blood Donation Management System** built with **Java Servlets, JSP and MySQL** following the MVC + DAO pattern. It connects **donors**, **patients** and **administrators**: patients raise blood requests, admins approve users and match donors, and donors receive notifications and track their donation history.

## ✨ Key Features

- 👤 Three roles — **Donor, Patient, Admin** with secure login (`AuthFilter`)
- 📝 Donor & patient **registration with admin approval / rejection**
- 🆘 Patients create **blood requests** and track request status
- 🔎 Admin **matches donors** to requests by blood group
- 🔔 **Donor notifications** — accept or reject donation requests
- 📜 **Donation history**, profiles and admin **reports dashboard**

## 🧠 How It Works

```mermaid
flowchart LR
P["🧑‍🦽 Patient Request"] --> A["🛡️ Admin Approval"] --> M["🔎 Donor Matching"] --> N["🔔 Donor Notified"] --> D["🩸 Donation Accepted"]
```

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java Servlets (MVC) + DAO layer |
| Views | JSP · HTML5 · CSS3 |
| Database | MySQL (JDBC) |
| Build / Server | Maven · Apache Tomcat |

## 📂 Project Structure

```text
Blood Donation Project/
├── pom.xml
└── src/main/
    ├── java/com/bloodbank/
    │   ├── controller/   # 25+ servlets (login, dashboards, requests, matching, reports)
    │   ├── dao/          # AdminDao, DonorDao, PatientDao, BloodRequestDao, ...
    │   ├── filter/       # AuthFilter
    │   └── listener/     # AppStartupListener
    └── webapp/           # JSP pages, CSS, assets
```

## 🚀 Getting Started

```bash
git clone https://github.com/PreciousBharath/BLOOD-DONATION.git
cd "BLOOD-DONATION/Blood Donation Project"
# 1. Create a MySQL database and update the DB credentials in the DAO/DB utility
mvn clean package
# 2. Deploy target/*.war to Apache Tomcat and open http://localhost:8080/
```

📄 Detailed project documentation is available in the `.docx` files and in `Blood Donation Project/README.md`.

## 🤝 Contributing

Contributions, issues and feature requests are welcome! Feel free to ⭐ star the repo and open a pull request.

## 👨‍💻 Author

<div align="center">

**Bharath** — Software Developer · Project Engineer · Technical Trainer

<a href="https://github.com/PreciousBharath"><img src="https://img.shields.io/badge/GitHub-PreciousBharath-181717?style=for-the-badge&logo=github"/></a>
<a href="https://linkedin.com/in/connectbharath"><img src="https://img.shields.io/badge/LinkedIn-connectbharath-0A66C2?style=for-the-badge&logo=linkedin"/></a>

### 🍔 EAT · 😴 SLEEP · 💻 CODE · 🔁 REPEAT

⭐ **If you found this project useful, give it a star!** ⭐

<img src="https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png" width="100%"/>

</div>
