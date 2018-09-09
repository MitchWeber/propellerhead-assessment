import { CustomerStatus } from '@/model/CustomerStatus';

export default class CustomerExcerpt {
    id: number;
    name: string;
    created: string;
    status: CustomerStatus;

    constructor(id: number, name: string, status:CustomerStatus, created: Date) {
      this.id = id;
      this.name = name;
      this.created = created.toLocaleString();
      this.status = status;
    }
}
