import Note from '@/model/Note';

export default class CustomerDetails {
  id!: number;
  name!: string;
  created!: Date;
  status!: string;
  email!: string;
  mobileNumber!: string;
  telefoneNumber!: string;
  notes = new Array<Note>();
}
