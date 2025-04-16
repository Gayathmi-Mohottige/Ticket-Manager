import React from "react";
import "./SimulationForm.css";

function FormInput ({label, name, value, onChange, error}) {
    const handleChange = (e) => {
        onChange(name, e.target.value);
    };

    return (
        <div className="form-input">
            <label>{label}</label>
            <input type="text" name={name} value={value} onChange={handleChange}/>
            {error ? <p className="error">{error}</p> : null}
        </div>
    );
}

export default FormInput;