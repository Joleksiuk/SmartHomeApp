import Button, { ButtonProps } from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';
import styled from '@emotion/styled';

export interface errorModalProps{
    setDeleteConfirm:any,
    open:boolean,
    message:string,
    setModalOpen:any,
}

const ColorButton = styled(Button)<ButtonProps>(({ theme }) => ({
    color: '#ffffff',
    backgroundColor: '#9c031d',
    '&:hover': {
      backgroundColor:'#520915',
    },
  }));
  
export default function AlertDialog(props:errorModalProps) {

  const handleClose = () => {
    props.setModalOpen(false)
  };
  const handleConfirm = () => {
    props.setDeleteConfirm(true);
    props.setModalOpen(false)
  };

  return (
    <div>
      <Dialog
        open={props.open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
        {props.message}
        </DialogTitle>
        <DialogActions>
            <ColorButton onClick={handleConfirm}  variant="contained">Confirm</ColorButton>
            <Button onClick={handleClose} autoFocus>Decline</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}