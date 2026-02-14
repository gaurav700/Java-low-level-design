# 🚗 Parking Lot System – Low Level Design (Java)

## 📌 Problem Statement

Design a Parking Lot System using Java.

The system should allow vehicles to:
- Enter the parking lot
- Get assigned a parking spot
- Exit the parking lot
- Free the parking spot upon exit

The system must support different vehicle types and parking spot types.

---

## 🏢 Parking Lot Structure

- The parking lot has multiple floors.
- Each floor has a fixed number of parking spots.
- Each spot belongs to one of the following types:
    - Bike Spot
    - Car Spot
    - Truck Spot

---

## 🚘 Vehicle Types

The system must support:
- Bike
- Car
- Truck

Rules:
- A vehicle can only park in a matching spot type.
- No cross parking allowed.
    - Car → Car Spot
    - Bike → Bike Spot
    - Truck → Truck Spot

---

## 🎯 Functional Requirements

### 1️⃣ Vehicle Entry
- When a vehicle enters:
    - System should find an available matching spot.
    - Assign that spot to the vehicle.
    - Mark spot as occupied.

### 2️⃣ Vehicle Exit
- When vehicle exits:
    - Spot should be freed.
    - Spot becomes available again.

---

## ❌ Out of Scope (For Now)

- No payment calculation
- No time tracking
- No database
- No concurrency handling
- No multiple entry/exit gates
- Everything should be in-memory

---

## 🧠 Design Constraints

- Follow proper OOP principles.
- Keep the design modular and extensible.
- Avoid tight coupling.
- Maintain separation of concerns.

---
