import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import "./styles/App.css";
import Login from "./pages/Login";
import DashBoard from "./pages/Dashboard";
import EditarProducto from "./components/EditarProducto";

function App() {
  return (
    <Router>
      <div className="overflow-hidden w-full bg-[#e6e6e6]">
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
