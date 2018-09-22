import CustomerStatus from "@/model/CustomerStatus";

interface Link {
  href: string;
  rel: string;
  type: string;
}

export default interface ICustomerExcerpt {
  entityId: number;
  name: string;
  created: Date;
  status: CustomerStatus;
  links: Array<Link>;
}

export default class CustomerExcerpt {
  entityId: number;
  name: string;
  created: Date;
  status: CustomerStatus;
  links: Array<Link>;

  constructor(excerpt: ICustomerExcerpt) {
    this.entityId = excerpt.entityId;
    this.name = excerpt.name;
    this.created = excerpt.created;
    this.status = excerpt.status;
    this.links = excerpt.links;
  }

  get linkToSelf(): string {
    return this.links.filter(link => link.rel === "self")[0].href;
  }
}
