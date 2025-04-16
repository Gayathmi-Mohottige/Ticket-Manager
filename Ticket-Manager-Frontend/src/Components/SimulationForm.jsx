import React, { useState, useEffect } from "react";
import axios from "axios";
import FormInput from "./FormInput";
import TicketPool from "./TicketPool";
import "./SimulationForm.css";

function SimulationForm () {
    const [formData, setFormData] = useState({
        totalTickets: "",
        ticketReleaseRate: "",
        customerRetrievalRate: "",
        maxTicketCapacity: "",
        numberOfVendors: "",
        numberOfCustomers: "",
    });

    const [error, setError] = useState({});

    const [simulationRunning, setSimulationRunning] = useState(false);

    const [ticketPoolSize, setTicketPoolSize] = useState(0);

    const handleInputChange = (name, value) => {
        setFormData((prevData) => ({...prevData, [name]:value}));
        setError((prevErrors) => ({...prevErrors, [name]: validateInput(name, value) }));
    };

    const validateInput = (name, value) => {
        if (value.trim() === "") return "This field is required.";
        if (!/^\d+$/.test(value)) return "Please enter a positive number.";
        if (parseInt(value, 10) <= 0) return "Value must be greater than 0.";
        return "";
      };

    const isFormValid = () => {
        const newErrors = {};
        let valid = true;
        Object.keys(formData).forEach((key) => {
            const error = validateInput(key, formData[key]);
            newErrors[key] = error;
            if (error) valid = false;
        });
        setError(newErrors);
        return valid;
    };

    const startSimulation = async () => {
        if (!isFormValid()) {
          alert("Fix the errors before starting.");
          return;
        }
        try {
          await axios.post("http://localhost:8081/api/tickets/start", formData);
          setSimulationRunning(true);
          alert("Simulation started!");
        } catch (error) {
          alert("Failed to start simulation.");
          console.error(error);
        }
      };

      const stopSimulation = async () => {
        try {
          await axios.post("http://localhost:8081/api/tickets/stop");
          setSimulationRunning(false);
          alert("Simulation stopped!");
        } catch (error) {
          alert("Failed to stop simulation.");
          console.error(error);
        }
      };
      
      useEffect(() => {
        if (simulationRunning) {
          const interval = setInterval(async () => {
            try {
              const response = await axios.get("http://localhost:8081/api/tickets/ticket-pool");
              setTicketPoolSize(response.data); 
            } catch (error) {
              console.error("Error fetching ticket pool size:", error);
            }
          }, 100);
      
          return () => clearInterval(interval); 
        }
      }, [simulationRunning]);
      

      return (
        <div className="simulation-form">
            <h1>Ticketing Platform</h1>
            <form
            onSubmit={(e) => {
                e.preventDefault();
                startSimulation();
            }} 
            >
                {[
                    { label: "Total Tickets", name: "totalTickets" },
                    { label: "Ticket Release Rate (ms)", name: "ticketReleaseRate" },
                    { label: "Customer Retrieval Rate (ms)", name: "customerRetrievalRate" },
                    { label: "Max Ticket Capacity", name: "maxTicketCapacity" },
                    { label: "Number of Vendors", name: "numberOfVendors" },
                    { label: "Number of Customers", name: "numberOfCustomers" },
                ].map(({label, name}) => (
                    <FormInput
                        key={name}
                        label={label}
                        name={name}
                        value={formData[name]}
                        onChange={handleInputChange}
                        error={error[name]}
                    />
                ))}
                <button type="submit" disabled={simulationRunning}>
                    Start Simulation
                </button>
                <button type="button" onClick={stopSimulation} disabled={!simulationRunning}>
                    Stop Simulation
                </button>
            </form>

            <TicketPool poolSize={ticketPoolSize} maxPool={formData.totalTickets ? parseInt(formData.totalTickets, 10) : 100} />
        </div>
      );
}

export default SimulationForm;