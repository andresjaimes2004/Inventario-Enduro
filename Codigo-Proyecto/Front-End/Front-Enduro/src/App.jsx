import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./styles/App.css";
import Login from "./pages/Login";
import DashBoard from "./pages/Dashboard";

function App() {
  return (
    <Router>
      <div className="overflow-hidden w-full h-screen bg-[#e6e6e6]">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/dashboard" element={<DashBoard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
