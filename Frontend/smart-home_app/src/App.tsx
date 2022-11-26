import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import SignIn from './authorization/SignIn';
import SignUp from './authorization/SignUp';
import Dashboard from './dashboard/Dashboard';

function App() {
  return (
    <BrowserRouter>
    <div className="App">
      <header className="App-header">
        <div>
        <Routes>
          <Route path="/" element={<Dashboard/>} />
          <Route path="/signIn" element={<SignIn />} />
          <Route path="/signUp" element={<SignUp />} />
        </Routes>     
        </div>
      </header>
    </div>
    </BrowserRouter>
  );
}

export default App;
