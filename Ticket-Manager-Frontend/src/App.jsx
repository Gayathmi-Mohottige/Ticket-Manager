import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SimulationForm from "./Components/SimulationForm";
import TransactionHistory from "./Components/TransactionHistory";
import RealTimeLogs from "./Components/RealTimeLogs"; 
import TicketChart from "./Components/TicketChart"; 
import NavBar from "./Components/NavBar";
import "./index.css"; // Import the main CSS file

function App() {
  return (
    <Router>
      <NavBar />
      <Routes>
        <Route 
          path="/" 
          element={
            <div className="dashboard-container">
              <div className="left-panel">
                <SimulationForm />
              </div>
              <div className="right-panel">
                <RealTimeLogs />
                <TicketChart />
              </div>
            </div>
          } 
        />
        <Route 
          path="/transactions" 
          element={
            <div className="elevated-card">
              <TransactionHistory />
            </div>
          } 
        />
      </Routes>
    </Router>
  );
}

export default App;