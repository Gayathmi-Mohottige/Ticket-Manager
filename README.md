# 🎟️ Ticket Manager System

A full-stack ticket simulation system built using **Spring Boot** (Backend) and **React** (Frontend). The system simulates real-time ticket generation and purchasing using manual multithreaded concurrency.

---

## 📚 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)

---

## 🚀 Project Overview

This project is a simulation-based **ticketing system** involving two core entities: **vendors** and **customers**. Vendors add tickets to a shared ticket pool, while customers purchase tickets from it. The backend is designed using Spring Boot and implements concurrency manually (without Spring annotations). The frontend, built with React, allows users to configure simulation parameters and monitor real-time activity.

---

## ✨ Features

### Backend
- Vendor and Customer simulation using multithreading.
- Real-time ticket pool updates.
- RESTful API endpoints for configuration and control.
- Transaction logging and persistence via MySQL.

### Frontend
- **Configuration Panel**: Set total tickets, release rate, purchase rate, and max capacity.
- **Vendor/Customer Count**: Adjust the number of vendors and customers.
- **Simulation Controls**: Start and stop buttons for simulation.
- **Live Pool Meter**: Real-time display of current ticket count.
- **Transaction Log Viewer**: Displays all transaction history.
- Fully styled using custom CSS.

---

## 🛠️ Technologies Used

### Backend
- Java SDK 11
- Spring Boot 2.7.14
- MySQL Driver

### Frontend
- React
- chart.js
- Axios
- npm
- CSS
