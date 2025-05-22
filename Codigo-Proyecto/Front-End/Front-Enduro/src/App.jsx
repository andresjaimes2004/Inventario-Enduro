import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import "./styles/App.css";
import Login from "./pages/Login";
import DashBoard from "./pages/Dashboard";

function App() {
  return (
    <Router>
      <div className="overflow-hidden w-full h-screen bg-[#e6e6e6]">
        <Routes>
          <Route path="/" element={<Navigate to="/login" replace />} />
          <Route path="/login" element={<Login />} />
          <Route path="/*" element={<DashBoard />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
