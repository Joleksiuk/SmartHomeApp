import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import SettingsIcon from '@mui/icons-material/Settings';
import { Link } from 'react-router-dom';
import HomeIcon from '@mui/icons-material/Home';
import AppShortcutIcon from '@mui/icons-material/AppShortcut';
import LightbulbIcon from '@mui/icons-material/Lightbulb';

export const mainListItems = (
  <React.Fragment>
     <Link to="/"> 
      <ListItemButton>
        <ListItemIcon>
          <HomeIcon />
        </ListItemIcon>
        <ListItemText primary="HomePage" />
      </ListItemButton>
    </Link>
    <Link to="/devices"> 
      <ListItemButton>
        <ListItemIcon>
          <AppShortcutIcon />
        </ListItemIcon>
        <ListItemText primary="Devices" />
      </ListItemButton>
    </Link>
    <Link to="/configAccounts"> 
    <ListItemButton>
      <ListItemIcon>
        <SettingsIcon />
      </ListItemIcon>
      <ListItemText primary="Configure accounts" />
    </ListItemButton>
    </Link>
    <Link to="/shellyDuo"> 
    <ListItemButton>
      <ListItemIcon>
        <LightbulbIcon />
      </ListItemIcon>
      <ListItemText primary="Shelly duo" />
    </ListItemButton>
    </Link>
    <Link to="/tuyaPlug"> 
    <ListItemButton>
      <ListItemIcon>
        <LightbulbIcon />
      </ListItemIcon>
      <ListItemText primary="Tuya plug" />
    </ListItemButton>
    </Link>
    <Link to="/tuyaLED"> 
    <ListItemButton>
      <ListItemIcon>
        <LightbulbIcon />
      </ListItemIcon>
      <ListItemText primary="Tuya LED" />
    </ListItemButton>
    </Link>
    <Link to="/test"> 
    <ListItemButton>
      <ListItemIcon>
        <SettingsIcon />
      </ListItemIcon>
      <ListItemText primary="Test Component" />
    </ListItemButton>
    </Link>
  </React.Fragment>
);

export const secondaryListItems = (
  <React.Fragment>
    {/* <ListSubheader component="div" inset>
      Saved reports
    </ListSubheader>
    <ListItemButton>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Current month" />
    </ListItemButton>*/}
  </React.Fragment>
);
