import { CustomerStatus } from '@/model/CustomerStatus';

export default class CustomerExcerpt {
    id!: number;
    name!: string;
    created!: Date;
    status!: CustomerStatus;
}
