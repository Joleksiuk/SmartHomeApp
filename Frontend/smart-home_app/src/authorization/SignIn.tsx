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
import authService from "./AuthService";
import { useEffect, useState } from 'react';
import { Alert, Stack } from '@mui/material';
import { Link,useNavigate } from 'react-router-dom';

const theme = createTheme();

export default function SignIn() {
  const [showError, setShowError] = useState<Boolean>(false);
  const [errorMessage, setErrorMessage] = useState<string>("");
  const [loginSuccessful, setLoginSuccessful]=useState<Boolean>(false);
  const navigate = useNavigate();
  
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    let username= data.get('username')?.toString();
    let password = data.get('password')?.toString();
    if(username===""){
      setShowError(true);
      setErrorMessage("Username can't be empty!");
      return;
    } 
    if(password===""){
      setShowError(true);
      setErrorMessage("Password can't be empty!");
      return;
    }
    if(username!==null && username!==undefined && password!=null && password!==undefined){
      authService.login(username,password)
      .then(() =>setLoginSuccessful(true))
      .then(() => navigate("/"))
      .catch(error => {
        setShowError(true);
        setErrorMessage("Wrong login credentials!");
      });  
    }
  };
  const goSignUp=()=>{
    navigate('signUp')
  }

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
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
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
              id="usernam"
              label="Username"
              name="username"
              autoComplete="username"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              autoComplete="current-password"
            />          
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item>
                <Button onClick={goSignUp}>
                  {"Don't have an account? Sign Up"}
                </Button>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
