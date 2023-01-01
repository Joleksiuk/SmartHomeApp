import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { useEffect, useState } from 'react';
import { Alert, Stack } from '@mui/material';
import { Link,useNavigate } from 'react-router-dom';
import ShellyService from '../../Services/ShellyService';
import AuthService from '../../authorization/AuthService';

const theme = createTheme();

export default function SignIn() {
  const [showError, setShowError] = useState<Boolean>(false);
  const [errorMessage, setErrorMessage] = useState<string>("");
  const [submitSuccessful, setSubmitSuccessful]=useState<Boolean>(false);
  const [accountConfigured, setAccountConfigured]=useState<Boolean>(false);
  
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    let server= data.get('server')?.toString();
    let authKey = data.get('authKey')?.toString();
    if(server===""){
      setShowError(true);
      setErrorMessage("Server link can't be empty!");
      return;
    } 
    if(authKey===""){
      setShowError(true);
      setErrorMessage("Authentication key can't be empty!");
      return;
    }
    if(authKey!==null && authKey!==undefined && server!=null && server!==undefined){
       ShellyService.createShellyUser(server,authKey)
       .then(() =>setSubmitSuccessful(true))
       .catch(error => {
         setShowError(true);
         setErrorMessage("Wrong login credentials!");
       });  
    }
    
  };

  useEffect(() => {
    if(AuthService.getLoggedUser()!==null){
      var userId = AuthService.getLoggedUser().id;
      
    }
   
  }, []);

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}>
          <img width="200" height="100"  src='https://i.postimg.cc/4dJPTCr0/shelly-logo.png'/>

          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          {showError &&
              <Grid item xs={12}>
                <Stack sx={{ width: '100%' }} spacing={2}>
                <Alert severity="error">{errorMessage}</Alert>
                </Stack>
              </Grid>
            }
            <TextField
              margin="normal"
              required
              fullWidth
              id="server"
              label="server link"
              name="server"
              autoComplete="server"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="authKey"
              label="Authentication key"
              type="authKey"
              id="authKey"
              autoComplete="Authentication key"
            />          
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Submit
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
