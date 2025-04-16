import React, { useState, useEffect } from "react";
import axios from "axios";
import TransactionRow from "./TransactionRow";
import "./TransactionHistory.css";

function TransactionHistory () {
    const [transactions, setTransactions] = useState([]);

    useEffect (() => {
        async function fetchTransaction() {
            try {
                const response = await axios.get("http://localhost:8081/api/tickets/transactions");
                setTransactions(response.data);
            } catch (error) {
                console.error("Error fetching transactions:", error);
            }
        }

        fetchTransaction();
    }, []);

    return (
        <div className="transaction-history">
          <h1>Transaction History</h1>
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Action</th>
                <th>Name</th>
                <th>Tickets</th>
                <th>Remaining in Pool</th>
              </tr>
            </thead>
            <tbody>
              {transactions.map((transaction) => (
                <TransactionRow key={transaction.id} transaction={transaction} />
              ))}
            </tbody>
          </table>
        </div>
      );
}

export default TransactionHistory;